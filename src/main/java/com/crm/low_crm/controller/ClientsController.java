package com.crm.low_crm.controller;

import com.crm.low_crm.model.enity.Address;
import com.crm.low_crm.repository.AddressRepository;
import com.google.common.collect.ImmutableMap;
import com.kuliginstepan.dadata.client.DadataClient;
import com.kuliginstepan.dadata.client.domain.Suggestion;
import com.kuliginstepan.dadata.client.domain.address.AddressRequestBuilder;
import de.micromata.opengis.kml.v_2_2_0.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@Slf4j
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientsController {

    private AddressRepository addressRepository;


    private DadataClient dadataClient;
    //82cdff - голубой #1e98ff 177bc9 Ffd21e - светло желтый #Ff931e E6761b #Ed4543 56db40 - светло зеленый #1bad03 - темно зеленый 97a100 595959 - черный #B3b3b3 F371d1 #B51eff #793d0e
    private static final Map<Integer,String> color = ImmutableMap.of(
            1, "1bad03",
            2, "Ff931e",
            3,"1e98ff",
            4,"B3b3b3",
            5,"793d0e",
            6,"B51eff",
            7,"Ed4543");
    @SneakyThrows
    @PostMapping( value = "/clientsLocateByXLSX",produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> clientsLocateByXLSX(@RequestParam("file") MultipartFile file){
        final Kml kml = new Kml();
        Document doc = kml.createAndSetDocument().withName(file.getOriginalFilename()).withOpen(true);
        Folder folder = doc.createAndAddFolder();
        folder.withName("Клиенты").withOpen(true);
        boolean startAnalizeData = false;
        XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(file.getInputStream()));
        XSSFSheet sheet = workbook.getSheetAt(0);
        Map<String,DataToFile> result = new HashMap<>();
        for(Row row : sheet){
            if(!startAnalizeData){
                if(row.getCell(0) == null){
                    continue;
                }
                if(row.getCell(0).getStringCellValue().equals("МК \"Линия Здоровья\"")){
                    startAnalizeData = true;
                }
            }
            else {
                if(row.getCell(0).getCellType() != CellType.STRING){
                    continue;
                }
                if(row.getCell(0).getStringCellValue().equals("Итого")){
                    break;
                }
                String address = row.getCell(0).getStringCellValue().toLowerCase();
                if(address.isEmpty()){
                    continue;
                }
                if(address.contains("квартира")){
                    address = address.substring(0, address.lastIndexOf(",", address.indexOf("квартира")));
                }
                Address adDB = addressRepository.findTopByAddressFrom1C(address);
                if(adDB == null){
                    List<Suggestion<com.kuliginstepan.dadata.client.domain.address.Address>> suggestions =  dadataClient.suggestAddress(AddressRequestBuilder.create(address).build()).collectList().block();
                    if(suggestions != null && suggestions.size() != 0){
                        adDB = new Address();
                        com.kuliginstepan.dadata.client.domain.address.Address adAddress = suggestions.get(0).getData();
                        adDB.setAddressFrom1C(address);
                        adDB.setFullAddress(suggestions.get(0).getValue());
                        adDB.setCity(adAddress.getCity());
                        adDB.setHouse(adAddress.getHouse());
                        adDB.setStreet(adAddress.getStreet());
                        adDB.setGeoLat(adAddress.getGeoLat());
                        adDB.setGeoLon(adAddress.getGeoLon());
                        addressRepository.save(adDB);
                    }
                    else {
                        adDB = new Address();
                        adDB.setAddressFrom1C(address);
                        adDB.setFullAddress(null);
                        adDB.setCity(null);
                        adDB.setHouse(null);
                        adDB.setStreet(null);
                        adDB.setGeoLat(0.0);
                        adDB.setGeoLon(0.0);
                        addressRepository.save(adDB);
                    }

                }
                if(adDB == null){
                    System.out.println(address);
                    break;
                }
                if(result.containsKey(adDB.getGeoLat() +","+adDB.getGeoLon())){
                    DataToFile data = result.get(adDB.getGeoLat() +","+adDB.getGeoLon());
                    data.setCount(data.getCount()+1);
                    data.setComment(data.comment+"\n"+ adDB.getAddressFrom1C());
                    result.put(adDB.getGeoLat() +","+adDB.getGeoLon(), data);
                }
                else{
                   result.put(adDB.getGeoLat() +","+adDB.getGeoLon(),
                           DataToFile.builder()
                                   .comment(adDB.getAddressFrom1C())
                                   .count(1)
                                   .latitude(adDB.getGeoLat())
                                   .longitude(adDB.getGeoLon())
                                   .build());
                }
            }

        }
        if(!result.isEmpty()){
            result.forEach((name, value) -> {createPlacemarkWithChart(folder,value);});
        }
        return ResponseEntity.ok().body(kml);
    }
    private static void createPlacemarkWithChart( Folder folder,DataToFile dataToFile) {
        Icon icon = new Icon()
                .withHref("http://api-maps.yandex.ru/i/0.4/micro/pmvvs.png");
        Placemark placemark = folder.createAndAddPlacemark();

        Style style = placemark.createAndAddStyle();
        Vec2 size = new Vec2();
        size.setX(7);
        size.setY(28);
        size.setXunits(Units.PIXELS);
        size.setYunits(Units.INSET_PIXELS);
        style.createAndSetIconStyle().withColor(color.getOrDefault(dataToFile.count,"595959").toUpperCase()).withHotSpot(size).withIcon(icon);
        placemark.withName(String.valueOf(dataToFile.count))
                .withDescription(
                        dataToFile.comment)
                .createAndSetLookAt().withLongitude(dataToFile.longitude).withLatitude(dataToFile.latitude).withAltitude(0).withRange(12000000);

        placemark.createAndSetPoint().addToCoordinates(dataToFile.longitude, dataToFile.latitude); // set coordinates
    }
    @Getter
    @Setter
    @Builder
    private static class DataToFile{
        private String comment;
        private int count;
        private Double longitude;
        private Double latitude;
    }
}
