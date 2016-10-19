package joshie.mirror.api;

import net.minecraft.util.ResourceLocation;

import java.util.Map;

public abstract class Element<E extends Element> {
    private final Ability ability;
    private final ResourceLocation resource;

    @SuppressWarnings("unchecked")
    public Element(ResourceLocation name, Ability ability) {
        this.ability = ability;
        this.resource = name;
        getMap().put(name, (E) this);
    }

    public abstract Map<ResourceLocation, E> getMap();

    public Ability getAbility() {
        return ability;
    }

    public ResourceLocation getResource() {
        return resource;
    }

    public abstract String getPrefix();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element<?> element = (Element<?>) o;
        return resource != null ? resource.equals(element.resource) : element.resource == null;

    }

    @Override
    public int hashCode() {
        return resource != null ? resource.hashCode() : 0;
    }
}
