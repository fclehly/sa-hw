angular.module('demo', ['ngDialog'])
    .controller('TableCtrl', function($scope, $http, ngDialog) {
        $scope.offset = 0
        $scope.limit = 10
        $http.get('http://localhost:8080/students?offset=' + $scope.offset + '&limit=' + $scope.limit).
        then(function(response) {
            $scope.students = response.data;
            console.log(response)
        })

        $scope.hello = function() {
            console.log("hello")
        }

        $scope.showAddStudentDialog = function() {
            ngDialog.open({template: 'templateDialogAdd.html',className: 'ngdialog-theme-default', scope: $scope})
        }

        $scope.showEditStudentDialog = function(student) {
            console.log(student)
            $scope.student = student
            ngDialog.open({template: 'templateDialogEdit.html',className: 'ngdialog-theme-default', scope: $scope})
        }

        $scope.showStudentScoreDialog = function(student) {
            // console.log(student)
            $scope.student = student
            ngDialog.open({template: 'templateDialogScore.html',className: 'ngdialog-theme-default', scope: $scope})
            
        }

        $scope.showStudentImportDialog = function() {
            // console.log(student)
            ngDialog.open({template: 'templateDialogImport.html',className: 'ngdialog-theme-default', scope: $scope})
            
        }



        $scope.addStudent = function(student) {

            console.log(JSON.stringify(student));
            $http.post('http://localhost:8080/students', JSON.stringify(student), {headers : {'Content-Type' : 'application/json'}})
            .then(function(response) {
                alert("添加成功")
                console.log(response)
            }) 
        }

        $scope.importStudent = function() {    
            var fd = new FormData();
            var file = document.querySelector('input[type=file]').files[0];
            fd.append('info', file); 
            // console.log(fd)
            $http({
                method:'POST',
                url:"http://localhost:8080/import",
                data: fd,
                headers: {'Content-Type':undefined},
                transformRequest: angular.identity 
                })   
                .success( function ( response )
                        {
                        //上传成功的操作
                        alert("uplaod success");
                        }); 

        }

        $scope.lastPageListener = function() {
            if ($scope.offset <= 0) {
                alert("没有了")
            } else {
                $scope.offset -= 10
                $http.get('http://localhost:8080/students?offset=' + $scope.offset + '&limit=' + $scope.limit).
                then(function(response) {
                    $scope.students = response.data;
                    console.log(response)
                })
            }
        }
        $scope.nextPageListener = function() {
            $scope.offset += 10
            $http.get('http://localhost:8080/students?offset=' + $scope.offset + '&limit=' + $scope.limit).
            then(function(response) {
                if (response.data.length <= 0) {
                    $scope.offset -= 10
                    alert("没有了")
                } else {
                    $scope.students = response.data;
                }
            })
        }
        $scope.deleteStudent = function(student) {
            $http.delete('http://localhost:8080/students/' + student.id, student)
            .then(function(response) {
                console.log(response.status)
                alert('删除成功，请刷新界面')
            })
            // console.log(student)
        }
        $scope.updateStudent = function(student) {
            $http.patch('http://localhost:8080/students/' + student.id, JSON.stringify(student), {headers : {'Content-Type' : 'application/json'}})
            .then(function(response) {
                console.log(response)
                alert("保存成功")
            })
            // closeThisDialog()
        }

    });