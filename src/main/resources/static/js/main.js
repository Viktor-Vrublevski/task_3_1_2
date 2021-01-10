$(document).ready(function (){
    $('.table-striped .btn-info').on('click', function (event) {

        event.preventDefault();
        const href = $(this).attr('href');
        $.get(href, function (person, status){
            $('.myForm #id').val(person.id);
            $('.myForm #name').val(person.name);
            $('.myForm #surname').val(person.surname);
            $('.myForm #age').val(person.age);
            $('.myForm #email').val(person.username);
            $('.myForm #pass').val(person.password);
            $('.myForm #role').val(person.role);
        });
        $('.myForm #editModal').modal();
    });
    
    $('.table-striped .btn-danger').on('click', function (event) {
        event.preventDefault();
        const href_2 = $(this).attr('href');
        $.get(href_2, function (person, status){
            $('.myDelete #id-d').val(person.id);
            $('.myDelete #name-d').val(person.name);
            $('.myDelete #surname-d').val(person.surname);
            $('.myDelete #age-d').val(person.age);
            $('.myDelete #email-d').val(person.username);
            $('.myDelete #pass-d').val(person.password);
            $('.myDelete #role-d-').val(person.role);
        });
        $('.myDelete #deleteModal').modal();
    });
});