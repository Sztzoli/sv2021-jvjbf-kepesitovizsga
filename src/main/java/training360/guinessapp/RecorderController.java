package training360.guinessapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/recorders")
@RequiredArgsConstructor
public class RecorderController {

    private final RecorderService recorderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecorderDto saveRecorder(
            @Valid @RequestBody RecorderCreateCommand command
    ) {
        return recorderService.saveRecorder(command);
    }

}
