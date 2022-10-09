const {Loc, Castle, Game, GameData} = require("../../../../../main/resources/apis/js/src/game");

const basicGameState = {
    "gameData": {
        "maxTurns": 1000,
        "currentTurn": 0
    },
    "objects": {
        "0": {
            "type": "CASTLE",
            "id": 0,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 1
            }
        },
        "1": {
            "type": "CASTLE",
            "id": 1,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 19
            }
        },
        "2": {
            "type": "CASTLE",
            "id": 2,
            "soldierType": "MELEE",
            "loc": {
                "row": 1,
                "col": 19
            }
        },
        "3": {
            "type": "CASTLE",
            "id": 3,
            "soldierType": "MELEE",
            "loc": {
                "row": 19,
                "col": 1
            }
        }
    }
}

const expectedGameObject = {
    // owner: 1,
    // logs: [],
    actions: [],
    gameData: new GameData(1000, 0),
    objects: {
        "0": new Castle(0, new Loc(1, 1), "MELEE"),
        "1": new Castle(1, new Loc(19, 19), "MELEE"),
        "2": new Castle(2, new Loc(1, 19), "MELEE"),
        "3": new Castle(3, new Loc(19, 1), "MELEE"),
    }
};
expectedGameObject.__proto__ = Game.prototype;

const basicGameStringString = JSON.stringify(basicGameState);

test("WHEN initializing game from json state SHOULD construct new game", () => {
    const game = new Game(basicGameStringString/*, 1*/);

    expect(game).toStrictEqual(expectedGameObject);
});


// test("GIVEN valid game WHEN getting allMyCastles SHOULD return only my castles", () => {
//     const game = new Game(basicGameStringString, -1);
//     const expectedCastles = [
//         new Castle(2, -1, new Loc(1, 19), "MELEE"),
//         new Castle(3, -1, new Loc(19, 1), "MELEE"),
//     ];
//
//     expect(game.getAllMyCastles()).toStrictEqual(expectedCastles);
// });

// test("GIVEN game when changing soldierType SHOULD add correct action to castle", () => {
//     const game = new Game(basicGameStringString)
//
//     const newSoldierType = "RANGED";
//     const castleId = 0;
//
//     game.changeSoldierType(castleId, newSoldierType);
//
//     expect(game.actions).toStrictEqual([new ChangeSoldierTypeAction(castleId, newSoldierType)]);
// });

// test("GIVEN game WHEN adding debug game logs SHOULD add push logs", () => {
//     const game = new Game(basicGameStringString, 1)
//     const expectedGame = new Game(basicGameStringString, 1)
//     const logMessage1 = "hello"
//     const logMessage2 = "world"
//     expectedGame.logs = [logMessage1, logMessage2]
//     game.log(logMessage1)
//     game.log(logMessage2)
//     expect(game).toStrictEqual(expectedGame);
// });