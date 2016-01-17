// CONFIG BLOCK
var backend_host = "localhost";
var backend_port = "8080";
// END CONFIG BLOCK

var app = angular.module("notes", []);

app.filter('reverse', function () {
    return function (items) {
        return items.slice().reverse();
    };
});

app.directive("controlEnter", function() {
    return function(scope, element, attrs) {
        element.bind("keydown keypress", function(event) {
            if(event.ctrlKey && (event.which === 13)) {
                scope.$apply(function() {
                    scope.$eval(attrs.controlEnter);
                });
                event.preventDefault();
            }
        });
    };
});

app.controller("list", ["$scope", "$http", "$timeout", function ($scope, $http, $timeout) {
    $scope.notes = [];
    $scope.alert = {
        hide: true,
        color: "success",
        heading: "Heads up!",
        text: "Something's comming!"        
    };
    
    $scope.hide_alert = function() {
        var alert = $scope.alert;
        alert.hide = true;
    };

    $scope.note = {
        title: null,
        content: null
    };

    $scope.save = function () {
        var note = $scope.note;
        $http({
            method: "PUT",
            url: "http://" + backend_host + ":" + backend_port + "/add-note",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            data: $.param(note)
        }).then(function (data) {
            console.log(data);
            $scope.note.title = "";
            $scope.note.content = "";
            $scope.load();
            $scope.alert = {
                hide: false,
                color: "success",
                heading: "Allright!",
                text: "The note has been saved"
            };
            $timeout(function() { $scope.alert.hide = true; }, 5000);
        }, function (data) { 
            $scope.load();            
         });
    };

    $scope.load = function () {
        $http({
            method: "GET",
            url: "http://" + backend_host + ":" + backend_port + "/notes"
        }).then(function (response) {
            var tasks = $scope.notes;
            var localTasks = [];
            
            for (var key in response.data) {
                // skip loop if the property is from prototype
                if (!response.data.hasOwnProperty(key)) continue;

                var obj = response.data[key];
                localTasks.push(obj);
            }
            $scope.notes = localTasks;
        }, function (response) {
            $scope.load();
        });
    };
    
    $scope.deleteNote = function(id) {
        $http({
            method: "DELETE",
            url: "http://" + backend_host + ":" + backend_port + "/delete-note?id=" + id,
            headers: {"Content-Type": "application/x-www-form-urlencoded"}            
        }).then(function(response) {
            $scope.load();
            $scope.alert = {
                hide: false,
                color: "success",
                heading: "Allright!",
                text: "The note has been deleted"
            };
            $timeout(function() { $scope.alert.hide = true; }, 5000);
        }, function() {            
            $scope.load();            
        });
    };

    $scope.load();
}]);