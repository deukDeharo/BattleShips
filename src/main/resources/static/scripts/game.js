$(document).ready(function(){

  $.ajax({
    type: 'GET',
    url: "api/game_view/" + getParameterUrl()

   }).done(function(data) {
      alert( "success" );
      checkBoats(data);
      var gameData = data;
      if(withBoats == true){
         $("#FirstFrame").hide();
         createGridWithLava("game","");
         createGridWithLava("oponent","alt_");
         putBoats(gameData);
         fireEveryone(gameData);
      }
      else{
      console.log("hay Barcos? "+ " "+ withBoats);
      createGridWithLava("addShipsGrid","alt2_");
      hoverEmptyBoats();
      $(".tile").click(function(){
      console.log("tilefunctionValid");
          if($(this).hasClass(chosenShip+0)){
              console.log(chosenShip);
              shipNumber++;
              chosenShip = shipsList[shipNumber].boat;
              chosenShipLength = shipsList[shipNumber].shipLength;
              console.log(chosenShip);
              }


          });



      }
      showData(gameData);
      showJson(gameData);
    })

    .fail(function() {
      alert( "error" );
    })
    /*.always(function() {
      alert( "complete" );
    });*/

});

var withBoats;
var repeat = false;
var times= 0;
var isHorizonatl = true;
var chosenShip = "Patrol";
var chosenShipLength = 2;
var alphabet = ["A","B","C","D","E","F","G","H","I","J"];
var locationPatrol = [];
var locationDestroyer = [];
var locationSubmarine = [];
var locationBattleShip = [];
var locationCarrier = [];
var shipNumber = 0;
var shipsList = [{boat:"Patrol", shipLength: 2},{boat:"Destroyer", shipLength: 3},{boat:"Submarine", shipLength: 3},{boat:"Battleship", shipLength: 4},{boat:"Carrier", shipLength: 5}];
//EVENT LISTENERS

$("#returnToGames").click(function(){
    location.assign("http://localhost:8080/games.html");

});

$("#horizontal").click(function(){
chekDirection();
});

$("#vertical").click(function(){
chekDirection();
});



/*$(".shipButton").click(function(){
    chosenShip = $(this).attr("value");
    chosenShipLength = $(this).attr('data-locationLenght');
    console.log(chosenShip +" "+chosenShipLength);
})*/



function hoverEmptyBoats(){


        $(".tile").hover(function(){


            if( $(this).attr("data-gridTile") > 0 && $(this).attr("data-gridTile") < 11 && $(this).children().length == 0 && $(this).hasClass("busyShipTile")==false ){
                  var actualNumber = parseInt($(this).attr("data-gridTile"));
                  var actualLetter = $(this).attr("data-gridLetter");
                  var hasBoat;
                  var count =0;

                    for(var q = 0; q<chosenShipLength; q++){
                        if($("#alt2_"+ actualLetter + (actualNumber +i)).hasClass("busyShipTile") == false){
                            count++;
                    }
                    }
                    if(chosenShipLength == count){
                    hasBoat = false;
                    }
                    else{
                    hasBoat = true;
                    }

                    if(hasBoat == false){
                    if(isHorizonatl){
                     for(var i = 0; i<chosenShipLength; i++){
                        $("#alt2_"+ actualLetter + (actualNumber +i)).css("opacity","0.7");
                        $("#alt2_"+ actualLetter + (actualNumber +i)).addClass(chosenShip+i);
                            }


 }
                    if(!isHorizonatl){
                        for(var i = 0; i<chosenShipLength; i++){
                            $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).css("opacity","0.7");
                            $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).addClass(chosenShip+i);
                        }



                    }


                    }

                     }},
            function(){
                if( $(this).attr("data-gridTile") > 0 && $(this).attr("data-gridTile") < 11 && $(this).children().length == 0){
                    var actualNumber = parseInt($(this).attr("data-gridTile"));
                    var actualLetter = $(this).attr("data-gridLetter");

                       if(isHorizonatl){
                           for(var i = 0; i<chosenShipLength; i++){
                              $("#alt2_"+ actualLetter + (actualNumber+i)).css("opacity","1");
                              $("#alt2_"+ actualLetter + (actualNumber+i)).removeClass(chosenShip+i);
                                    }
                                  }
                        if(!isHorizonatl){
                           for(var i = 0; i<chosenShipLength; i++){
                           $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).css("opacity","1");
                           $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+i] + actualNumber ).removeClass(chosenShip+i);
                                                                  }
                                                              }
                                        }
                                     }

    )};

function chekDirection(){
    if($("#horizontal").is(':checked')){
        isHorizonatl = true;
    }else{
        isHorizonatl = false; }
};


function getParameterUrl(){
    var url = window.location.search;

    return url.substring(4);
};

function showData(gameData){


    $("#data").append('<p class="p_data" id="creationDate_p">Date of Creation:'+ " " + gameData.Date + '</p>');
    for(var i= 0; i<gameData.participation.length; i++){
        if(gameData.participation[i].id == getParameterUrl()){
             $("#data").append('<p class="p_data" id="player_p" > Player:'+ " " + gameData.participation[i].player.userName + "</p>");
        }else{
        $("#data").append('<p class="p_data" id="oponent_p" > Oponent:'+ " " + gameData.participation[i].player.userName + "</p>");
        }

    }

};

/*function clickBoats(){
    console.log("Clickboats: " + "."+chosenShip+0);
    $("."+chosenShip+0).click(function(){
        console.log(chosenShip);
        shipNumber++;
        chosenShip = shipsList[shipNumber].boat;
        chosenShipLength = shipsList[shipNumber].shipLength;
        console.log(chosenShip);


    })
}*/

function fillTheEmptyTiles(){
    for(var w = 0; w < chosenShipLength; w++){
        $("#alt2_"+ alphabet[alphabet.indexOf(actualLetter)+w] + actualNumber )
    }

}

var json;
function showJson(gameData){
    json= gameData;
    console.log(json);
};

function fireEveryone (gameData){

    for(var i = 0; i<gameData.Salvoes.length; i++){
        if(gameData.Salvoes[i].Player == getParameterUrl()){
            fireShips(gameData,"alt_",i);
        }
        else {
            fireShips(gameData,"",i);
        }
    }

};
function fireShips(gameData,div, i){
    for (var z = 0; z<gameData.Salvoes[i].Salvos.length; z++){
        for(var q= 0; q<gameData.Salvoes[i].Salvos[z].locations.length; q++){

         $("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).empty().removeClass("tile").removeClass("lavaAnim").addClass("tileFired")
         .append("<p class='p_turnData'>"+ gameData.Salvoes[i].Salvos[z].turn+"</p>");
            if($("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).hasClass("ship")== true){
            $("#" + div + gameData.Salvoes[i].Salvos[z].locations[q]).removeClass("turned").attr("style", 'background-image: url("../assets/terrain/scope.png");')
            }
        }
    }
};
function addShips(){

var ships = [{ boatType:"Submarine",locationList:["H2",
                                                                "H3",
                                                                "H4"]  },
                                        {boatType:"Patrol",locationList:["B8","B9"
                                                                               ]  },
                                         {boatType:"Carrier",locationList:["I1","I2","I3","I4","I5"
                                                                                                           ]  },
                                        {boatType:"BattleShip",locationList:["A2","B2","C2","D2"
                                                                                                          ]  },
                                         {boatType:"Destroyer",locationList:["A1","B1","C1"
                                                                                                           ]  },
                                        ];

    $.post({
      url: "/api/ships/" + getParameterUrl() ,
      data: JSON.stringify(ships),
      dataType: "text",
      contentType: "application/json"
    })

    .done(function (response, status, jqXHR) {
      alert( "ship added: " + response );
    })
    .fail(function (jqXHR, status, httpError) {
        console.log(jqXHR);
      alert("Failed to add Ship: " + status + " " + httpError);
    })

//location.reload();

};

function checkBoats(data){
    if(data.Ships.length == 0){
        withBoats=false;
    }
    else{
        withBoats=true;
    }
}
