package edu.tamu.scholars.middleware.messaging;

import static edu.tamu.scholars.middleware.messaging.EntityAction.CREATE;

public class CreateEntityMessage<E> extends SaveEntityMessage<E> {

    public CreateEntityMessage(E entity) {
        super(entity);
    }

    public EntityAction getAction() {
        return CREATE;
    }

}
