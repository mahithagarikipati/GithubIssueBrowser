
var details= angular.module("details", ["ngMessages","ngMaterial","ngAria"]);

details.controller("detailsController", [ '$scope', '$http','$window','$mdDialog', function($scope, $http,$window,$mdDialog) {
     $scope.addFields = function(ev) {
     $http({
         url : 'HomeService',
         method : "POST",
         params : {
             'issueId' : id,
             'mode':'GETDATA'
         }
     }).then(function(response,$window) {
         console.log(response.data);
         $scope.object = response.data;
         var container = document.getElementById("container");
         while (container.hasChildNodes()) {
             container.removeChild(container.lastChild);
         }
             var para = document.createElement("p");
             var input = document.createElement("textarea");
             input.maxLength ="400";
             input.rows ="30";
              input.setAttribute('ng-model',response.data.issue_id);
              input.value = "Issue ID  :: " +response.data.id+"\r\n"+"Issue Number ::"+response.data.issue_id+" "+"\r\n"+"Created on ::"+response.data.created+" "+"\r\n"
              + "Updated On ::: " +response.data.updated+"\r\n" + "Author association :: " +response.data.author+" "+"\r\n" 
              + "Body ::: " +response.data.body+"\r\n" + "Url : " +response.data.url;
            
            para.appendChild(input);
            container.appendChild(para);
        })
    }     ;
    

     
}]);
