package com.iot.data_server.command_markers;

import com.iot.data_server.entities.DataEntity;
import com.iot.data_server.exporters.SensorDataExcelExporter;
import com.iot.data_server.services.SensorDataService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.List;

@ShellComponent
@AllArgsConstructor
public class DataServerCommands {

    private final SensorDataService sensorDataService;

    @ShellMethod(value = "export to csv")
    public void export() throws IOException {
        List<DataEntity> dataEntityList = sensorDataService.ListAll();
        SensorDataExcelExporter excelExporter = new SensorDataExcelExporter(dataEntityList);
        excelExporter.export();
    }
    @ShellMethod(value = "Clear database")
    public void clearData() {
        sensorDataService.clearData();
    }
}
