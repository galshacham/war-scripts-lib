const {Loc, Castle, Game, MapData, ChangeSoldierTypeAction} = require("../src/game");

const basicGameState = {
    // TODO: add proxy
    owner:1,
    mapData: {
        rows: 20,
        cols: 20,
        players: [0, 1],
    },
    gameObjects: [
        {
            objectType: "CASTLE",
            owner: 0,
            id: 0,
            creatingSoldierType: "MELEE",
            loc: {
                row: 1,
                col: 1,
            },
        },
        {
            objectType: "CASTLE",
            owner: 1,
            id: 1,
            creatingSoldierType: "MELEE",
            loc: {
                row: 19,
                col: 19,
            },
        },
        {
            objectType: "CASTLE",
            owner: -1,
            id: 2,
            creatingSoldierType: "MELEE",
            loc: {
                row: 1,
                col: 19,
            },
        },
        {
            objectType: "CASTLE",
            owner: -1,
            id: 3,
            creatingSoldierType: "MELEE",
            loc: {
                row: 19,
                col: 1,
            },
        },
    ],
};

const expectedGameObject = {
    owner: 1,
    logs: [],
    actions: [],
    mapData: new MapData(20, 20, [0, 1]),
    gameObjects: [
        new Castle(0, 0, new Loc(1, 1), "MELEE"),
        new Castle(1, 1, new Loc(19, 19), "MELEE"),
        new Castle(2, -1, new Loc(1, 19), "MELEE"),
        new Castle(3, -1, new Loc(19, 1), "MELEE"),
    ],
};
expectedGameObject.__proto__ = Game.prototype;

const basicGameStringString = JSON.stringify(basicGameState);

test("whenInitializingGameFromJsonState_shouldConstructNewGame", () => {
    const game = new Game(basicGameStringString, 1);

    expect(game).toStrictEqual(expectedGameObject);
});

test("givenValidGame_whenGettingAllMyCastles_shouldReturnOnlyMyCastles", () => {
    const game = new Game(basicGameStringString, -1);
    const expectedCastles = [
        new Castle(2, -1, new Loc(1, 19), "MELEE"),
        new Castle(3, -1, new Loc(19, 1), "MELEE"),
    ];

    expect(game.getAllMyCastles()).toStrictEqual(expectedCastles);
});

test("givenGame_whenChangingSoldierType_shouldAddCorrectActionToCastle", () => {
    const game = new Game(basicGameStringString)

    const newSoldierType = "RANGED";
    const castleId = 0;

    game.changeSoldierType(castleId, newSoldierType);

    expect(game.actions).toStrictEqual([new ChangeSoldierTypeAction(castleId, newSoldierType)]);
});

test("givenGame_whenAddingDebugGameLogs_shouldAddPushLogs", () => {
    const game = new Game(basicGameStringString, 1)
    const expectedGame = new Game(basicGameStringString, 1)
    const logMessage1 = "hello"
    const logMessage2 = "world"
    expectedGame.logs = [logMessage1, logMessage2]
    game.log(logMessage1)
    game.log(logMessage2)
    expect(game).toStrictEqual(expectedGame);
});


test("check", () => {
    const action = new ChangeSoldierTypeAction(1, "MELEE")
});
