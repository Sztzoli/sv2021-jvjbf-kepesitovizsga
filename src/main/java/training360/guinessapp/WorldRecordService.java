package training360.guinessapp;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

@Service
@RequiredArgsConstructor
public class WorldRecordService {

    private final WorldRecordRepository wordRecordRepository;
    private final RecorderService recorderService;
    private final ModelMapper mapper;

    public WorldRecordDto saveWorldRecord(WorldRecordCreateCommand command) {
        Recorder recorder = recorderService.findById(command.getRecorderId());
        WorldRecord wordRecord = wordRecordRepository.save(
                new WorldRecord(
                        command.getDescription(),
                        command.getValue(),
                        command.getUnitOfMeasure(),
                        command.getDateOfRecord(),
                        recorder));
        return mapper.map(wordRecord, WorldRecordDto.class);
    }
}
