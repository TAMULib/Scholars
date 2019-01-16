package edu.tamu.scholars.middleware.messaging;

public abstract class SaveEntityMessage<E> implements EntityMessage {

    private final E entity;

    public SaveEntityMessage(E entity) {
        this.entity = entity;
    }

    public E getEntity() {
        return entity;
    }

}
