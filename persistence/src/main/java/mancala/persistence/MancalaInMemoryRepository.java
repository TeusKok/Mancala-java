package mancala.persistence;

import java.util.HashMap;

import mancala.domain.IMancala;

public class MancalaInMemoryRepository implements IMancalaRepository {

    HashMap<String,IMancala> memory;

    public MancalaInMemoryRepository(){
        memory = new HashMap<String,IMancala>();
    }

    @Override
    public void save(String key, IMancala game) {
        memory.put(key,game);
       
    }

    @Override
    public IMancala get(String key) {
        return memory.get(key);
    }
    
}
