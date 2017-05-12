
var alfabeto = ["null","Null","A","B","C","D","E","F","G","H","I","J","Nulo"];



function putVotes(gameData){
    /*var battleShip = [[{distance:5,type:"Carrier"}],
                     [{distance:4,type:"BattleShip"}],
                     [{distance:3,type:"Submarine"}],
                     [{distance:3,type:"Destroyer"}],
                     [{distance:2,type:"Patrol"}]];

    var patrol = battleShip[4][0];
    var destroyer = battleShip[3][0];
    var submarine = battleShip[2][0];
    var battle_Ship = battleShip[1][0];
    var carrier = battleShip[0][0];*/

for (var i = 0; i<gameData.Ships.length; i++){
   //if(gameData.Ships[i].type == battleShip[i][0].type)
    for(var z = 0; z<gameData.Ships[i].locations.length; z++){

        $("#"+gameData.Ships[i].locations[z]).append("<img src='../assets/terrain/"+gameData.Ships[i].type+"_0"+(z+1)+".gif'>");

        if(gameData.Ships[i].locations[0][0] === gameData.Ships[i].locations[1][0]){
        $("#"+gameData.Ships[i].locations[z]).addClass("turned");
        }


    }
    }
}


//createGridWithLava();

var ejeI = 12;
//var eje0 = 1;

function createGridWithLava(){

for(var i = 0; i<alfabeto.length; i++){

     $("<div class='row'></div>").attr('id', (alfabeto[i]+"_"+i).toString()).appendTo("#game");



     for (var z = 0; z < alfabeto.length; z++){
      if(i === 0 && z === 0 ){
            $("<div class='tile'></div>").attr('id', (alfabeto[i]+(z-1)).toString()).attr("style", 'background-image: url("../assets/terrain_3(1)_01.gif")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }

       else if(i === 12 && z === 0 ){
                   $("<div class='tile'></div>").attr('id', alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_09.2.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
               }
        else if(i === 12 && z === ejeI ){
                           $("<div class='tile'></div>").attr('id', alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_11.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
                       }
        else if(i === 0 && z === ejeI ){
                           $("<div class='tile'></div>").attr('id', alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_03.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
                       }
        else if (i === 0 && z > 0 && z<ejeI){
         $("<div class='tile'></div>").attr('id', alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_02.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }
        else if (i === 12 && z > 0 && z < ejeI){
         $("<div class='tile'></div>").attr('id', alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_10.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }
        else if (i<ejeI && i>0 && z===0){
         $("<div class='tile'></div>").attr('id', alfabeto[i]+ (z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_05.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }
        else if (i<ejeI && i>0 && z===ejeI){
         $("<div class='tile'></div>").attr('id', alfabeto[i]+ (z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_07.jpg")').appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }

        else{
            $("<div class='tile'></div>").attr('id', alfabeto[i]+ (z-1)).appendTo("#"+(alfabeto[i]+"_"+i).toString());
        }
    }

}
printCoordenates();
randomLavaAnimation(13);

}
//random lava


function randomLavaAnimation (numberOfTiles){
    for(var i = 0; i < numberOfTiles +1; i++){
        var x = Math.round(Math.random() * (10 - 1) + 1);
        var y = Math.round(Math.random() * (10 - 1) + 1);
        $("#"+alfabeto[x]+y).attr("style", 'background-image: url("../assets/terrain/lava.gif")');
                };
         };
function printCoordenates(){
    for (var i = 1; i<11; i++){
        $("#"+ alfabeto[i+1]+0).append("<p class='coordenate'>"+ alfabeto[i+1] + "</p>");
        $("#Null"+i).append("<p class='coordenate'>" + i + "</p>");
    }
}


