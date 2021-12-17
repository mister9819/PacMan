package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.object.character.ACharacter;

public interface IUpdateStrategy {

    String getName();

    void updateState(ACharacter context);

    void updateState(ACharacter context, ACharacter pacman);
}
