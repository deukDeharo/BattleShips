
var alfabeto = ["null","Null","A","B","C","D","E","F","G","H","I","J","Nulo"];



function putBoats(gameData){

for (var i = 0; i<gameData.Ships.length; i++){
   //if(gameData.Ships[i].type == battleShip[i][0].type)
    for(var z = 0; z<gameData.Ships[i].locations.length; z++){

        $("#"+gameData.Ships[i].locations[z]).addClass("ship").append("<img src='../assets/terrain/"+gameData.Ships[i].type+"_0"+(z+1)+".gif'>");

        if(gameData.Ships[i].locations[0][0] === gameData.Ships[i].locations[1][0]){
        $("#"+gameData.Ships[i].locations[z]).addClass("turned");
        }


    }
    }
}


//createGridWithLava();

var ejeI = 12;
//var eje0 = 1;

// en el parametro se escribe el nombre del div del player o del oponente == "game" o "oponent"
// en el tile_id se escribe el nombre de la id del grid de los jugadores// en el PLAYER = " "; en el OPONENT = "alt_"
function createGridWithLava(div,tile_id){

for(var i = 0; i<alfabeto.length; i++){

     $("<div class='row'></div>").attr('id', (tile_id + alfabeto[i]+"_"+i).toString()).appendTo("#"+div);



     for (var z = 0; z < alfabeto.length; z++){
      if(i === 0 && z === 0 ){
            $("<div class='tile'></div>").attr('id', (tile_id + alfabeto[i]+(z-1)).toString()).attr("style", 'background-image: url("../assets/terrain_3(1)_01.gif")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }

       else if(i === 12 && z === 0 ){
                   $("<div class='tile'></div>").attr('id',tile_id +  alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_09.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
               }
        else if(i === 12 && z === ejeI ){
                           $("<div class='tile'></div>").attr('id', tile_id + alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_11.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
                       }
        else if(i === 0 && z === ejeI ){
                           $("<div class='tile'></div>").attr('id', tile_id + alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_03.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
                       }
        else if (i === 0 && z > 0 && z<ejeI){
         $("<div class='tile'></div>").attr('id',tile_id +  alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_02.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }
        else if (i === 12 && z > 0 && z < ejeI){
         $("<div class='tile'></div>").attr('id',tile_id +  alfabeto[i]+(z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_10.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }
        else if (i<ejeI && i>0 && z===0){
         $("<div class='tile'></div>").attr('id',tile_id +  alfabeto[i]+ (z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_05.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }
        else if (i<ejeI && i>0 && z===ejeI){
         $("<div class='tile'></div>").attr('id',tile_id +  alfabeto[i]+ (z-1)).attr("style", 'background-image: url("../assets/terrain_3(1)_07.png")').appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }

        else{
            $("<div class='tile'></div>").attr("data-gridLetter", alfabeto[i]).attr("data-gridTile", (z-1)).attr('id',tile_id +  alfabeto[i]+ (z-1)).appendTo("#"+(tile_id + alfabeto[i]+"_"+i).toString());
        }
    }

}

printCoordenates(tile_id);
randomLavaAnimation(13,tile_id);

}
//random lava

//lo mismo aquí. Se especifica el id de la tile, si en la plantilla del oponente ("alt_") o del Player ("")//
function randomLavaAnimation (numberOfTiles, tile_id){
    for(var i = 0; i < numberOfTiles +1; i++){
        var x = Math.round(Math.random() * (10 - 1) + 1);
        var y = Math.round(Math.random() * (10 - 1) + 1);
        $("#"+ tile_id + alfabeto[x]+y).addClass("lavaAnim");
                };
         };
function printCoordenates(tile_id){
    for (var i = 1; i<11; i++){
        $("#"+ tile_id +alfabeto[i+1]+0).append("<p class='coordenate'>"+ alfabeto[i+1] + "</p>");
        $('#' + tile_id + "Null"+i).append("<p class='coordenate'>" + i + "</p>");
    }
}



