package com.othello;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.othello.model.Human;
import com.othello.model.IA_lvl_0;

@RunWith(MockitoJUnitRunner.class)
public class FactoryTest {
  
    @InjectMocks
    private FactoryPlayer factory;

    @Test
    public void FactoryPlayerTest() {
        assertTrue(FactoryPlayer.createPlayer("Human", 'X') instanceof Human);
        assertTrue(FactoryPlayer.createPlayer("IA_lvl_0", 'O') instanceof IA_lvl_0);
        assertFalse(FactoryPlayer.createPlayer("Human", 'X') instanceof IA_lvl_0);
        assertFalse(FactoryPlayer.createPlayer("IA_lvl_0", 'O') instanceof Human);
    }

}
