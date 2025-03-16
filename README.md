```mermaid
classDiagram

class Pokemon {
    <<Entity>>
    +Long id
    +String name
    +Generation generation
    +Region region
    +List<Type> types
}

class Generation {
    <<Entity>>
    +Integer id
    +String name
}

class Region {
    <<Entity>>
    +String id
    +String name
}

class Type {
    <<Enumeration>>
    // FIRE, WATER, GRASS, ...
}

Pokemon --> "1" Generation : "muitos Pokémon → 1 Generation"
Pokemon --> "1" Region : "muitos Pokémon → 1 Region"
Pokemon --> "1..*" Type : "possui"

```