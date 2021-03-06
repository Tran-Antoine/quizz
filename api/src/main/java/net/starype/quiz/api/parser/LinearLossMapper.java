package net.starype.quiz.api.parser;

import net.starype.quiz.api.database.ReadableRawMap;
import net.starype.quiz.api.answer.LinearLossFunction;
import net.starype.quiz.api.answer.LossFunction;

/**
 * Mapper for the {@link LinearLossFunction} object
 */
public class LinearLossMapper implements ConfigMapper<LossFunction> {

    @Override
    public String getMapperName() {
        return "linear";
    }

    @Override
    public LossFunction map(ReadableRawMap config) {
        return new LinearLossFunction();
    }
}
