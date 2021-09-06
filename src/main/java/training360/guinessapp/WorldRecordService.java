package training360.guinessapp;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.guinessapp.dto.BeatWorldRecordCommand;
import training360.guinessapp.dto.BeatWorldRecordDto;
import training360.guinessapp.dto.WorldRecordCreateCommand;
import training360.guinessapp.dto.WorldRecordDto;

import javax.transaction.Transactional;

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

    @Transactional
    public BeatWorldRecordDto updateWorldRecord(Long id, BeatWorldRecordCommand command) {
        Recorder recorder = recorderService.findById(command.getRecorderId());
        WorldRecord worldRecord = findById(id);
        if (command.getNewRecord() < worldRecord.getValue()) {
            throw new InvalidValueException(command.getNewRecord(), worldRecord.getValue());
        }
        BeatWorldRecordDto beatWorldRecordDto = getBeatWorldRecordDto(command, recorder, worldRecord);
        worldRecord.setRecorder(recorder);
        worldRecord.setValue(command.getNewRecord());

        return beatWorldRecordDto;
    }

    private BeatWorldRecordDto getBeatWorldRecordDto(BeatWorldRecordCommand command, Recorder recorder, WorldRecord worldRecord) {
        return new BeatWorldRecordDto(
                worldRecord.getDescription(),
                worldRecord.getUnitOfMeasure(),
                worldRecord.getRecorder().getName(),
                worldRecord.getValue(),
                recorder.getName(),
                command.getNewRecord(),
                command.getNewRecord() - worldRecord.getValue()
        );
    }

    private WorldRecord findById(Long id) {
        return wordRecordRepository
                .findById(id).orElseThrow(() -> new WorldRecordNotFoundException(id));
    }
}
