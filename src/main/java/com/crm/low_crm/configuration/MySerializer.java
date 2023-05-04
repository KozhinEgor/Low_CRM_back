package com.crm.low_crm.configuration;

import com.crm.low_crm.model.subDto.ReportDataDto;
import com.crm.low_crm.model.view.ViewReport;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class MySerializer extends JsonSerializer<ViewReport> {

    public MySerializer() {
    }

    @Override
    public void serialize(ViewReport viewReport, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        if(viewReport.getDate() != null){
            gen.writeObjectField(viewReport.getDate().toString(), new ReportDataDto(viewReport.getCount(), viewReport.getSecondCount()));
        }
        else if(viewReport.getWeek() != null && viewReport.getYear() != null){
            gen.writeObjectField(viewReport.getWeek() + "W" + viewReport.getYear() + "Y", new ReportDataDto(viewReport.getCount(), viewReport.getSecondCount()));
        }
        else if(viewReport.getMonth() != null && viewReport.getYear() != null){
            gen.writeObjectField(viewReport.getMonth() + "M" + viewReport.getYear()+ "Y", new ReportDataDto(viewReport.getCount(), viewReport.getSecondCount()));
        }
        else if( viewReport.getYear() != null){
            gen.writeObjectField(viewReport.getYear()+ "Y", new ReportDataDto(viewReport.getCount(), viewReport.getSecondCount()));
        }
        else {
            gen.writeObjectField("not period", new ReportDataDto(viewReport.getCount(), viewReport.getSecondCount()));
        }
        gen.writeEndObject();
    }
}
