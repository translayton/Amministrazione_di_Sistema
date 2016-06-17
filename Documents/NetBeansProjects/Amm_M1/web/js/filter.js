/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (){
   $("#filtra").keyup(function(){  

        var q = $("#filtra").val();
       
        $.ajax({
            url: "filter.json",
            data:{
              cmd: "filter",
              q: q
            },
            dataType: 'json',
            success : function(data, state){
                updateItemList(data);
            },
            error : function(data, state){
            }
        });
        
        function updateItemList(itemList){
            $(".pari").remove();
            $(".dispari").remove();
            $("#mex").removeClass("center");
            $("#mex").addClass("hide");
            
            if(itemList.length !== 0){
                var i = 0;
                $("table").removeClass("hide");
                
                for(var item in itemList){
                    
                    var tr = document.createElement("tr"); 
                    
                    var td = document.createElement("td");
                    var txt = document.createTextNode(itemList[item].name);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    
                    var td = document.createElement("td");
                    var img = document.createElement("img");
                    img.setAttribute("src", "./M3/" + itemList[item].img.src);
                    img.setAttribute("alt", itemList[item].img.alt);
                    img.setAttribute("width", itemList[item].img.width);
                    img.setAttribute("height", itemList[item].img.height);
                    td.appendChild(img);
                    tr.appendChild(td);
                   
                    var td = document.createElement("td");
                    var txt = document.createTextNode(itemList[item].amount);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    
                    var td = document.createElement("td");
                    var txt = document.createTextNode(itemList[item].price);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    
                    var td = document.createElement("td");
                    var form = document.createElement("form");
                    form.setAttribute("action", "cliente.html");
                    form.setAttribute("method", "post");
                    var input = document.createElement("input");
                    input.setAttribute("type", "hidden");
                    input.setAttribute("name", "obj");
                    input.setAttribute("value", itemList[item].id);
                    form.appendChild(input);
                    var input = document.createElement("input");
                    input.setAttribute("type", "submit");
                    input.setAttribute("name", "Cart");
                    input.setAttribute("value", "Aggiungi al carrello");
                    form.appendChild(input);
                    td.appendChild(form);
                    td.className+= " cart";
                    tr.appendChild(td);
                    
                    if(i%2===0){
                        tr.className += " pari";
                    }
                    else{
                        tr.className += " dispari";
                    }
                    
                    document.getElementById("itemList").appendChild(tr);
                    i++;
                } 
            }
            else{
                $("table").addClass("hide");
                var p = document.getElementById("mex");
                if(!p.hasChildNodes()){
                    var txt = document.createTextNode("Nessun risultato!");
                    p.appendChild(txt);
                }
                p.className -= " hide";
                p.className += " center";
            } 
            
        }
    }); 
    
    $("#filtra").focus(function(){
        this.value='';
    });
});
