package training360.guinessapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worldrecords")
@RequiredArgsConstructor
public class WorldRecordController {

    private final WorldRecordService wordRecordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto saveWorldRecord(
            @Valid @RequestBody WorldRecordCreateCommand command
    ) {
        return wordRecordService.saveWorldRecord(command);
    }
}
