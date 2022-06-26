const {Loc, Castle, Game, MapData} = require("../src/game");

const basicGameState = {
    "mapData": {
        "rows": 20,
        "cols": 20,
        "players": [
            0,
            1
        ]
    },
    "gameObjects": [
        {
            "objectType": "CASTLE",
            "player": 0,
            "id": "0",
            "creatingSoldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 1
            }
        },
        {
            "objectType": "CASTLE",
            "player": 1,
            "id": "1",
            "creatingSoldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 19
            }
        },
        {
            "objectType": "CASTLE",
            "player": -1,
            "id": "2",
            "creatingSoldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 19
            }
        },
        {
            "objectType": "CASTLE",
            "player": -1,
            "id": "3",
            "creatingSoldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 1
            }
        }
    ]
}

const expectedGameObject = {
    mapData: new MapData(20, 20, [0, 1]),
    gameObjects: [
        new Castle("0", 0, new Loc(1, 1), "MELEE"),
        new Castle("1", 1, new Loc(19, 19), "MELEE"),
        new Castle("2", -1, new Loc(1, 19), "MELEE"),
        new Castle("3", -1, new Loc(19, 1), "MELEE"),
    ],
    player: 1
}
expectedGameObject.__proto__ = Game.prototype

const basicGameStringString = JSON.stringify(basicGameState)

test("whenInitializingGameFromJsonState_shouldConstructNewGame", () => {
    const game = new Game(basicGameStringString, 1);

    expect(game).toStrictEqual(expectedGameObject)
})

test("givenValidGame_whenGettingAllMyCastles_shouldReturnOnlyMyCastles", () => {
    const game = new Game(basicGameStringString, -1);
    const expectedCastles = [
        new Castle("2", -1, new Loc(1, 19), "MELEE"),
        new Castle("3", -1, new Loc(19, 1), "MELEE")]

    expect(game.getAllMyCastles()).toStrictEqual(expectedCastles)
})