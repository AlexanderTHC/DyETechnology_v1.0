/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
// TABLA Sub Categoria
$(document).ready(function () {
    $('#tablaSubCategorias').DataTable({
        lengthMenu: [5, 10, 15, 20],

        language: {
            "search": "Buscar: ",
            "lengthMenu": "Mostrar _MENU_ registros",
            "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
            "zeroRecords": "No hay registros",
            "infoEmpty": "No hay registros",
            "infoFiltered": "(Encontrados _MAX_ de registros)",
            "paginate": {
                "first": "Primero",
                "last": "Último",
                "previous": "Anterior",
                "next": "Siguiente"
            }
        }
    });
    
    $('.money').mask('#.##0,00', {reverse: true});
});
/*************************************  METODO PARA BORRAR POR AJAX *************************************/
function borrar(id) {
    //	console.log(id);
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });
        swal.fire({
            title: '¡Advertencia!',
          text: "¿Quieres eliminar esta SubCategoria?, No podras revertir la acción",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#218838',
            confirmButtonText: 'Eliminar',
            cancelButtonColor: '#dc3545',
            cancelButtonText: 'Cancelar',
            reverseButtons: true,
            padding: '1rem',
            width: '31%',
            backdrop: true,
            allowOutsideClick: false,
            allowEscapeKey: false,
            allowEnterKey: false,
            stopKeydownPropagation: false,
            showConfirmButton: true
        })
                .then((result) => {
    
                    if (result.isConfirmed) {
                        $.ajax({
                            url: "/subcategorias/activo/" + id,
                            success: function (res) {
                                console.log(res);
                            },
                        });
                        swal.fire({
                            title: '¡Éxito!',
                          text: "Categoria eliminada",
                            icon: 'success',
                            confirmButtonColor: '#218838',
                            confirmButtonText: 'Aceptar',
                            reverseButtons: true,
                            padding: '1rem',
                            width: '31%',
                            backdrop: true,
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            allowEnterKey: false,
                            stopKeydownPropagation: false,
                            showConfirmButton: true
                        })
                                .then((result) => {
                                    if (result.isConfirmed) {
                                        location.href = "/subcategorias/listado";
                                    }
                                });
                    } else if (
                            /* Read more about handling dismissals below */
                            result.dismiss === Swal.DismissReason.cancel
                            ) {
                        swal.fire({
                            title: 'Cancelado',
                          text: "No se eliminó la SubCategoria",
                            icon: 'error',
                            confirmButtonColor: '#dc3545',
                            confirmButtonText: 'Aceptar',
                            reverseButtons: true,
                            padding: '1rem',
                            width: '31%',
                            backdrop: true,
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                            allowEnterKey: false,
                            stopKeydownPropagation: false,
    //                        showConfirmButton: true
                        });
                    }
                });
    }