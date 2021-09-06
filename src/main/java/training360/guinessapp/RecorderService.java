package training360.guinessapp;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.RecorderCreateCommand;
import training360.guinessapp.dto.RecorderDto;

@Service
@RequiredArgsConstructor
public class RecorderService {

    private final RecorderRepository recorderRepository;
    private final ModelMapper mapper;


    public RecorderDto saveRecorder(RecorderCreateCommand command) {
        Recorder recorder = recorderRepository.save(new Recorder(command.getName(), command.getDateOfBirth()));
        return mapper.map(recorder, RecorderDto.class);
    }

    public Recorder findById(Long id) {
        return recorderRepository.findById(id).orElseThrow(() -> new RecorderNotFoundException(id));
    }
}
