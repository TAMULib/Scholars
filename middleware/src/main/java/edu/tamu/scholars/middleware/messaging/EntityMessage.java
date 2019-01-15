package edu.tamu.scholars.middleware.messaging;

public class EntityMessage<E> {

    private E entity;

    private EntityAction action;

    public EntityMessage(E entity, EntityAction action) {
        this.entity = entity;
        this.action = action;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E entity) {
        this.entity = entity;
    }

    public EntityAction getAction() {
        return action;
    }

    public void setAction(EntityAction action) {
        this.action = action;
    }

    public static <E> EntityMessage<E> create(E entity) {
        return new EntityMessage<E>(entity, EntityAction.CREATE);
    }

    public static <E> EntityMessage<E> update(E entity) {
        return new EntityMessage<E>(entity, EntityAction.UPDATE);
    }

    public static <E> EntityMessage<E> delete(E entity) {
        return new EntityMessage<E>(entity, EntityAction.DELETE);
    }

}
