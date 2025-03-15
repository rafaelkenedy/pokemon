classDiagram
class Pokemon {
+Long id
+String name
}

    class Generation {
        +String name
    }

    class Region {
        +String name
    }

    class Type {
        +String name
    }

    Pokemon --> Generation : pertence
    Pokemon --> Region : pertence
    Pokemon --> "1..*" Type : possui
