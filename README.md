# DII-GEN6-CompoundSEC
This is project-based learning for CAMT-DII gen6 students

We will incrementally utilze OOP concept to develop the security system for a compound (resident buildings).
The importances of the project is allow student to do
1. Self-learning: read the related course materials in advance of lecture and learn to associate concept of lecture to the actual code using issue on Github
2. Backlog refinement: with question from self-learning, student must draft the issue on the backlog of current iteration. This draft of backlog will be discussed in class.
3. Dev-ready card: at the end of lecture session, student should clear all issues with lecturer and TA to fully understand the cost and process to move the card to ready tag.
4. In progress and In review: are tags that allow lecturer and TA to consult and review with student on the lab session.
5. Done: after reviewed, student can move the issues to the done tag to conclude the finished task on the current iteration.

**Backlog refinement** is the important step for student. The backlog can only create by student who did the self-study and develop the project to some extend.

# Design Brief
1. Access control system for multi-floor system (low floor, medium floor, high floor)
- Access cards with multi-facades id and time-based encryption
- Floor level access control and room level access control
2. Audit trial for card access
- Each attempts will be logged with necessary info
- Card generation or modification will be logged with necessary info
3. Card management 
- Add, modify, revoke permission of each card

classDiagram
    %% Core Card Management System
    class CardAccessAbstract {
        <<abstract>>
        +getCardNumber() String
        +getCardPermission() ArrayList~String~
        #encryptData(String data) String
        #decryptData(String data) String
        #generateCardId() String
    }

    class CardAccess {
        -cardId: String
        -cardLevel: ArrayList~String~
        +CardAccess(String cardLevel)
        +getCardNumber() String
        +getCardPermission() ArrayList~String~
        +setCardLevel(String newLevel) void
    }

    class CardManagementInterface {
        <<interface>>
        +addCard(CardAccess card) void
        +modifyCard(String cardId, String newLevel) void
        +revokeCard(String cardId) void
        +getCard(String cardId) CardAccess
    }

    class CardManagement {
        -cardList: ArrayList~CardAccess~
        +CardManagement()
        +addCard(CardAccess card) void
        +modifyCard(String cardId, String newLevel) void
        +revokeCard(String cardId) void
        +getCard(String cardId) CardAccess
    }

    %% GUI Components
    class GUIbackend {
        -cardManager: CardManagement
        +GUIbackend()
        +handleCardOperations()
        +processRequests()
    }

    class GUIfrontend {
        -components: JFrame
        +GUIfrontend()
        +displayInterface()
        +updateView()
    }

    %% Logging System
    class Logs {
        -logHistory: ArrayList~String~
        +logUpdate(String text) void
        +getLogs() ArrayList~String~
        +clearLogs() void
    }

    %% Main Application
    class Main {
        +main(String[] args) void
    }

    %% Relationships
    CardAccessAbstract <|-- CardAccess : extends
    CardManagementInterface <|.. CardManagement : implements
    CardManagement o-- CardAccess : manages
    Main --> GUIbackend : creates
    GUIbackend --> CardManagement : uses
    GUIbackend --> CardAccess : manages
    Main --> GUIfrontend : creates
    GUIfrontend --> CardManagement : uses
    CardManagement --> Logs : logs operations
    Logs --> GUIbackend : provides logs
