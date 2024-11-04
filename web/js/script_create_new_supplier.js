$(document).ready(function(){
    $(document).on('click', '#add_supplier', function(event){
        event.preventDefault(); 
        var idWarehouser = $('#memberInfo').data('id');
        var name_supplier = $('#keyword_search').val();
        createNewSupplier(idWarehouser, name_supplier);
    });
});

function createNewSupplier(idWarehouser, name_supplier) {
    $.ajax({
        url: 'create_new_supplier', 
        data: { idWarehouser: idWarehouser, name_supplier: name_supplier },
        type: 'POST',
        dataType: 'json',
        success: function(response) {
            if (response.status === "success") {
//                window.location.href = "/PTTK_2024_21/list_ingredient";
                alert(response.message);
            } else {
                alert(response.message);
            }
        },
        error: function() {
            alert("An error occurred while create new supplier.");
        }
    });
}