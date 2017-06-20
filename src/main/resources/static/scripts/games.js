$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/games"

   }).done(function(data) {
      alert( "success" );
      console.log(data.Games);
      createTable(data);
      checkUser(data);
      if(isLogged === true){
       createGamesTable(data);
       createJoinGamesTable(data);
       $("#loginDiv").css("display", "none");
          $("#displayLogin").hide();
          $("#displaySignin").hide();



      };

      //createList(data);
    })
    .fail(function() {
      alert( "error" );
    })
    /*.always(function() {
      alert( "complete" );
    });*/



});

var isLogged = false;





//EVENT LISTENERS

$("#displayLogin").click(function(){
    $("#signinDiv").css("display", "none");
    $("#loginDiv").css("display", "block");
})

$("#displaySignin").click(function(){
    $("#loginDiv").css("display", "none");
    $("#signinDiv").css("display", "block");
})

$("#loginButton").click(function(evt){
    login(evt);
})

$("#logOutButton").click(function(evt){
    logout(evt);
})

$("#signinButton").click(function(evt){
    signin(evt);
})

$(".enterGame").click(function(){

console.log("click");
var gp = this.attr("gameplayer");
})

$(document).on("click",".enterGame", function(evt){
    var gp = evt.target.id;
    location.assign("http://localhost:8080/game.html?gp="+gp);


})


$(document).on("click","#newGameButt", function(evt){
    console.log(evt);
    newGame();


})

$(document).on("click",".joinButt", function(evt){
    joinGame(evt);


})


//FUNCIONES

function createTable(data){
      var leaderTable = data.LeaderBoard;
      leaderTable.sort(function(a, b){return b.TotalScore-a.TotalScore});
      console.log(leaderTable);

      $("<table id='tableScore'/>").appendTo("#LeaderBoard").addClass("table table-hover table-bordered");
      $("<tbody id='scoreResults' />").appendTo("#tableScore");
      $("<tr/>").attr("id","leaderRowTitles").appendTo("#scoreResults");
      $("<th/>").addClass("tableTitle").text("UserName").appendTo("#leaderRowTitles");
      $("<th/>").addClass("tableTitle").text("Wins").appendTo("#leaderRowTitles");
      $("<th/>").addClass("tableTitle").text("Loses").appendTo("#leaderRowTitles");
      $("<th/>").addClass("tableTitle").text("Ties").appendTo("#leaderRowTitles");
      $("<th/>").addClass("tableTitle").text("Total Score").appendTo("#leaderRowTitles");
      for (var i = 0; i<leaderTable.length; i++){
          $("<tr/>").attr("id","leaderRow"+i).addClass("rowLeaderTab").appendTo("#scoreResults");
          $("<td/>").addClass("leaderTab").appendTo("#leaderRow"+i).text(leaderTable[i].userName)
          $("<td/>").addClass("leaderTab").appendTo("#leaderRow"+i).text(leaderTable[i].win)
          $("<td/>").addClass("leaderTab").appendTo("#leaderRow"+i).text(leaderTable[i].looses)
          $("<td/>").addClass("leaderTab").appendTo("#leaderRow"+i).text(leaderTable[i].ties)
          $("<td/>").addClass("leaderTab").appendTo("#leaderRow"+i).text(leaderTable[i].TotalScore)

      }

  };

function login(evt) {
  evt.preventDefault();
  var form = evt.target.form;
  $.post("/api/login",
         { userName: form["userName"].value,
           password: form["password"].value })
   .done(function(a,b,c){
   alert("Logged success")
   $("#loginDiv").css("display", "none");
   $("#displayLogin").hide();
   $("#displaySignin").hide();
   location.reload();

      })
      .fail(function(a){
       alert(a.responseJSON.error);
      })

   };

function logout(evt) {
  evt.preventDefault();
  $.post("/api/logout")
   .done(function(a,b,c){
    location.reload();
   })
   .fail(function(a){
    alert(a.responseJSON.error);
   })

};

function signin(evt){
    evt.preventDefault();
    var form = evt.target.form;
    $.post("/api/players",
            { userName: form["userName"].value,
              password: form["password"].value })
              .done(function(textMssg,state,jqXHR) {
              alert(jqXHR.responseText);
              $.post("/api/login",
                       { userName: form["userName"].value,
                         password: form["password"].value })
              $("#signinDiv").css("display", "none");
              $("#displayLogin").hide();
                 $("#displaySignin").hide();
                 location.reload();

               })

               .fail(function(a,b,c) {
                             alert(a.responseText);
                             $("#signinDiv").css("display", "none");
                              });

};

function checkUser(data){
    var objects = Object.keys(data);

    if(objects[0] == "CurrentPlayer"){
        isLogged = true;
    }

    else{

        isLogged = false;
    }


};

function createGamesTable(data){
         var currentPlayer = data.CurrentPlayer;
        /* console.log(currentPlayer);
         console.log(currentPlayer.userName);
         console.log(currentPlayer.games[0]);
         console.log(currentPlayer.games[2].gamesScores);*/

         $("<h2 class='leaderTitle'>" + currentPlayer.userName +"</h2>").appendTo("#currentPlayerData");
         $("<div/>").attr("id","gamesContent" ).appendTo("#currentPlayerData");
         $("<h3 class='leaderTitle'>My Games</h3>").appendTo("#gamesContent");
         $("<button id='newGameButt'> New Game </button>").appendTo("#gamesContent");
          $("<div/>").attr("id","gamesTable" ).appendTo("#gamesContent");

        if(currentPlayer.games.length != 0){

         for(var i = 0; i<currentPlayer.games.length; i++){
         $("<div/>").attr("id", "gameDiv"+i).attr("class", "gameDiv").appendTo("#gamesTable");
         $("<h4> Game"+ currentPlayer.games[i].game.id + "</h4>").appendTo("#gameDiv"+i);
         $("<p> Started : " + currentPlayer.games[i].game.date + "</p>").appendTo("#gameDiv"+i);
         if(currentPlayer.games[i].game.gamesScores.length == 0){
            $("<button class='enterGame' id='"+currentPlayer.games[i].gameplayer+"'/>").text("Continue").appendTo("#gameDiv"+i)
                        .attr("gameplayer", currentPlayer.games[i].gameplayer);
         }
         else{
            $("<button class='enterGame' id='"+currentPlayer.games[i].gameplayer+"'/>").text("Finished").appendTo("#gameDiv"+i)
            .attr("gameplayer", currentPlayer.games[i].gameplayer);

            }

         }



         }
};

function newGame(){
    $.post("api/newGame");
    location.reload();

}

function createJoinGamesTable(data){
console.log("funcion ejecutada");

var games = data.Games;
var currentPlayer = data.CurrentPlayer.userName;

$('<h2 class="leaderTitle">Join Game</h2>').appendTo("#emptyGames");
$("<table id='joinGameTable' > </table>").appendTo("#emptyGames").addClass("table table-hover table-bordered");
$("<tbody id='joinGameTb'> </tbody>").appendTo("#joinGameTable");


    for(var i = 0; i<games.length; i++){

        if(games[i].participation[0].player.userName != currentPlayer){

            if(games[i].participation.length<2){
                $("<tr id='joinGameRow"+i+"'> </tr>").appendTo("#joinGameTb");
                $("<td class='leaderTab'> Game"+games[i].id + "</td>").appendTo("#joinGameRow"+i);
                $("<td class='leaderTab'> Created by "+games[i].participation[0].player.userName + "</td>").appendTo("#joinGameRow"+i);
                $("<td class='leaderTab'> <button class='joinButt' id='" + games[i].id + "'> Join </button></td>").appendTo("#joinGameRow"+i);



        }
      }

    }

}

function joinGame(evt){

    var gmId = evt.target.id;
    console.log(gmId);

    $.post("api/joinGame/"+gmId);
    location.reload();




}


