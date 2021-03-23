/**
 * 
 */

 var home= angular.module("home", ["ngMessages","ngMaterial","ngAria","ui.bootstrap"]);

 home.controller("issueController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {

     $scope.addFields = function(ev) {
     $http({
         url : 'HomeService',
         method : "POST",
         params : {
             'mode':'DATA'
         }
     }).then(function(response,$window) {
        
         console.log(response.data);
      
         $scope.array = response.data;
         var number = response.data.length; 
         var container = document.getElementById("container");
         while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
         }
         for (i=0;i<number;i++){
         var para = document.createElement("p");
                  var input = document.createElement("textarea");
             input.maxLength ="500";
             input.rows ="2";
              input.setAttribute('ng-model',response.data[i].issue_id);
              input.value =response.data[i].issue_id+" - "  +response.data[i].issue +"\r\n"
              +"Status of the issue : " +response.data[i].state;
            var button = document.createElement("input");
            button.type = "button";
             button.value = "Details";
             var id = response.data[i].issue_id;
             button.onclick =( function (id) {
                 return function(){
                 window.location.href = "IssueDetails.html?id="+id;   }       })( id);
             para.appendChild(input);
             para.appendChild(button);
             //container.appendChild(para);
             
         }
         if(number==0){
             var input = document.createElement("p");
              input.innerText ="No Issues listed! You can relax now!!!!!";
             container.appendChild(input);
             container.appendChild(document.createElement("br"));
             
         }
         $scope.curPage = 1,
         $scope.itemsPerPage = 10,
         $scope.maxSize = 10;
          
         this.container = response.data;
          
         $scope.numOfPages = function () {
         return Math.ceil(response.data.length / $scope.itemsPerPage);
          
         };
          
         $scope.$watch('curPage + numPerPage', function() {
         var begin = (($scope.curPage - 1) * $scope.itemsPerPage),
         end = begin + $scope.itemsPerPage;
          
         $scope.filteredItems =  response.data.slice(begin, end);
         });
         })
     }        
}]);
/**
 * 
 */