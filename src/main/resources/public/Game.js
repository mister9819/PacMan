import TileMap from "./TileMap.js";
import Pacman from "./Pacman.js";
import Ghost from "./Ghost.js";

const tileSize = 36;

const canvas = document.getElementById("game");
const ctx = canvas.getContext("2d");


const wallCanvas = document.getElementById("wall");
const wallCtx = wallCanvas.getContext("2d");

let tileMap;
var pacman, blue, pink, red, yellow;


let gameInterval;
let level;
let ghostNumber;

window.setWall = function setWall(src) {
    tileMap.setWall(src);
    tileMap.drawWall(wallCtx);
}

window.onload = function () {
    init();
    $("#playButton").click(() => {
        if (gameInterval !== undefined) {
            clearInterval(gameInterval)
        }
        gameInterval = setInterval(gameLoop, 1000 / 12);
    })

    $('#ghostDropdown .dropdown-item').click((evt) => {
        ghostNumber = parseInt(evt.target.innerText)
    })

    $('#levelDropdown .dropdown-item').click((evt) => {
        level = parseInt(evt.target.innerText)
    })

    $("#resetButton").click(() => {
        if (gameInterval !== undefined) {
            clearInterval(gameInterval)
        }
        init();
    })
}

function init() {
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    level = level ? level : 1;
    console.log(ghostNumber)
    ghostNumber = ghostNumber ? ghostNumber : 4;
    console.log(ghostNumber)
    pink = undefined
    red = undefined
    yellow = undefined

    $.post("/init", {level: level, ghostNumber: ghostNumber}, function (data) {
        tileMap = new TileMap(tileSize);
        tileMap.setMap(data);
        tileMap.setCanvasSize(canvas);
        tileMap.setCanvasSize(wallCanvas);

        const pacmanPos = tileMap.getPacman();
        pacman = new Pacman(pacmanPos[0], pacmanPos[1], tileSize)
        let ghostPos = tileMap.getGhost("blue");
        // inky
        blue = new Ghost(ghostPos[0], ghostPos[1], tileSize, "blue");
        if (ghostNumber > 3) {
            ghostPos = tileMap.getGhost("pink");
            // pinky
            pink = new Ghost(ghostPos[0], ghostPos[1], tileSize, "pink");

        }
        if (ghostNumber > 1) {
            ghostPos = tileMap.getGhost("red");
            // blink
            red = new Ghost(ghostPos[0], ghostPos[1], tileSize, "red");

        }
        if (ghostNumber > 2) {
            ghostPos = tileMap.getGhost("yellow");
            // cyl
            yellow = new Ghost(ghostPos[0], ghostPos[1], tileSize, "yellow");

        }
        setTimeout(() => {
            tileMap.draw(ctx);
            pacman.draw(ctx, true);
            blue.draw(ctx, true);
            tileMap.drawWall(wallCtx);

            if (red)
                red.draw(ctx, true);
            if (yellow)
                yellow.draw(ctx, true);
            if (pink)
                pink.draw(ctx, true);
        }, 100)
    }, "json");
    $("#highestScore").text(window.localStorage.getItem('score'));

}

function gameLoop() {
    $.get("/update", function (data) {
        console.log(data)
        if (!data.isGameOver) {
            updateLives(data.lives)
            for (let i = 0; i < data.characters.length; i++) {
                let currObj = data.characters[i];
                if (currObj.name === "pacman") {
                    pacman.updatePosition(currObj.position.x, currObj.position.y, tileSize);
                    pacman.keydown(currObj.direction)
                } else if (currObj.name === "ghost") {
                    if (currObj.ghostName === "pinky") {
                        if (currObj.ghostStatus == "dead") {
                            pink.changeGhost("eyes");
                        } else {
                            if (currObj.frightenTime > 0) {
                                if (currObj.frightenTime > 500) {
                                    pink.setBlink();
                                }
                                pink.changeGhost("frighten")
                            } else {
                                pink.changeGhost("pink")
                            }
                        }
                        pink.updatePosition(currObj.position.x, currObj.position.y, tileSize);
                        pink.keydown(currObj.direction)
                    } else if (currObj.ghostName === "clyde") {
                        if (currObj.ghostStatus == "dead") {
                            yellow.changeGhost("eyes");
                        } else {
                            if (currObj.frightenTime > 0) {
                                if (currObj.frightenTime > 500) {
                                    yellow.setBlink();
                                }
                                yellow.changeGhost("frighten")
                            } else {
                                yellow.changeGhost("yellow")
                            }
                        }
                        yellow.updatePosition(currObj.position.x, currObj.position.y, tileSize);
                        yellow.keydown(currObj.direction)
                    } else if (currObj.ghostName === "blinky") {
                        if (currObj.ghostStatus == "dead") {
                            red.changeGhost("eyes");
                        } else {
                            if (currObj.frightenTime > 0) {
                                if (currObj.frightenTime > 500) {
                                    red.setBlink();
                                }
                                red.changeGhost("frighten")
                            } else {
                                red.changeGhost("red")
                            }
                        }
                        red.updatePosition(currObj.position.x, currObj.position.y, tileSize);
                        red.keydown(currObj.direction)
                    } else {
                        if (currObj.ghostStatus == "dead") {
                            blue.changeGhost("eyes");
                        } else {
                            if (currObj.frightenTime > 0) {
                                if (currObj.frightenTime > 500) {
                                    blue.setBlink();
                                }
                                blue.changeGhost("frighten")
                            } else {
                                blue.changeGhost("blue")
                            }
                        }
                        blue.updatePosition(currObj.position.x, currObj.position.y, tileSize);
                        blue.keydown(currObj.direction)
                    }
                }
            }
            tileMap.setMap(data.map);
            $("#score").text(data.score)
            ctx.clearRect(0, 0, canvas.width, canvas.height)
            let hasDrawnDot = tileMap.draw(ctx);
            if (!hasDrawnDot) {
                level += 1;
                init()
            }
            pacman.draw(ctx, false);
            if (blue) {
                blue.draw(ctx, false);
            }
            if (pink) {
                pink.draw(ctx, false);
            }
            if (red) {
                red.draw(ctx, false);
            }
            if (yellow) {
                yellow.draw(ctx, false);
            }
        } else {
            if (data.score > $("#highestScore").val()) {
                console.log("new height score:"+ data.score +""+$("#highestScore").val())
                $("#highestScore").text(data.score)
                window.localStorage.setItem("score", data.score);
            }
            updateLives(0)
            ctx.clearRect(0, 0, canvas.width, canvas.height)
            ctx.font = "30px Arial";
            ctx.textAlign = "center"
            ctx.fillStyle = "#fa0000";
            ctx.fillText("Game Over!", canvas.width / 2, canvas.height / 2);
            console.log("game over")
        }
    }, "json");
}

// Handling pacman rotation, need to change this to be updated by backend
document.addEventListener("keydown", keydown);

function keydown() {
    $.post("/keypress", {keypress: event.keyCode}, function (data) {
        // console.log(data)
    }, "json");
}