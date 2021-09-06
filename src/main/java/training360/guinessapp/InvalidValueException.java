package training360.guinessapp;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidValueException extends AbstractThrowableProblem {

    public InvalidValueException(Double newValue, Double oldValue) {
        super(
                URI.create("world-record/invalid-value"),
                "Can not beat",
                Status.BAD_REQUEST,
                String.format("Can not beat: %f smaller than wr: %f", newValue, oldValue)
        );
    }
}

