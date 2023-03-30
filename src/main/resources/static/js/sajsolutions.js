// SECTOR POPPER //
const button = document.querySelector('#button');
const tooltip = document.querySelector('#tooltip');


const popperInstance = Popper.createPopper(button, tooltip, {
    placement: 'right',
  modifiers: [
    {
      name: 'offset',
      options: {
        offset: [0, 8],
      }
    }
  ]
});

// FIN POPPER //

// SECTOR MODALES //

// MODAL INCIAR LOGIN //
$("#modalbtnLogin").on("click", () => {
  $(".form-control").val("");
 //limpiarErrores(); 
  $("#modalLogin").modal("show");
});

// MODAL INCIAR a REGISTRAR //
$("#btnRegUser").on("click", () => {
  $("#modalLogin").modal("hide");
  $(".form-control").val("");
// limpiarErrores(); 
  $("#modalRegUser").modal("show");
});

// MODAL CERRAR REGISTRAR a INICIAR LOGIN //
$("#btnLogUser").on("click", () => {
  $(".form-control").val("");
 // limpiarErrores(); 
  $("#modalLogin").modal("show");
  $("#modalRegUser").modal("hide");
});

// FIN MODALES //

// SECTOR INICIAR SESION POR MODAL//
$("#btnIngresar").on("click", () => {

  //Crear un objeto articulo:
  let usuario = {};

  usuario.email = $("#emailUser").val();
  usuario.password = $("#passUser").val();

  $.ajax({
      method: "GET",
      url: "/ingresar",
      data: usuario,
      beforeSend: function () {
      //    limpiarErrores();
      },
      success: function () {
          $("#modalLogin").modal("hide");
          //alert("Producto guardado!");
          Swal.fire({
              title: '¡Éxito!',
              text: 'Ingresaste con Exito',
              icon: 'success',
              confirmButtonText: 'Continuar'
          }).then((result) => {
              if (result.isConfirmed) {
                  location.reload();
              }
          });
      },
      complete: function () {

      },
      /*statusCode: {
          422: function (xhr) {
              let errors = $.parseJSON(xhr.responseText);
              console.log("Errores: " + xhr.status);
              $.each(errors, function (key, val) {
                  $("#" + key).addClass("is-invalid");
                  $("#error-" + key).addClass("invalid-feedback")
                          .append("<span class='error-span'>" + val + "</span>");
              });
          }
      }*/
  });

});

// SECTOR REGISTRAR POR MODAL//
$("#btnRegistrar").on("click", () => {

  //Crear un objeto articulo:
  let usuario = {};

  usuario.id = 0;

  usuario.nombre = $("#nombreUser").val();
  usuario.apellido = $("#apellidoUser").val();
  usuario.correoElectronico = $("#emailUser").val();
  usuario.clave = $("#passUser").val();
  usuario.dni = $("#dniUser").val();
  usuario.nickName = $("#nickUser").val();
  usuario.permiso = 2;


  $.ajax({
      method: "POST",
      url: "/usuario/guardar",
      data: usuario,
      beforeSend: function () {
      //    limpiarErrores();
      },
      success: function () {
          $("#modalRegUser").modal("hide");
          //alert("Producto guardado!");
          Swal.fire({
              title: '¡Éxito!',
              text: 'Te registraste con Exito',
              icon: 'success',
              confirmButtonText: 'Continuar'
          }).then((result) => {
              if (result.isConfirmed) {
                  location.reload();
              }
          });
      },
      complete: function () {

      },
   /*   statusCode: {
          422: function (xhr) {
              let errors = $.parseJSON(xhr.responseText);
              console.log("Errores: " + xhr.status);
              $.each(errors, function (key, val) {
                  $("#" + key).addClass("is-invalid");
                  $("#error-" + key).addClass("invalid-feedback")
                          .append("<span class='error-span'>" + val + "</span>");
              });
          }
      } */
  });

});

/*const limpiarErrores = () => {
  $(".is-invalid").removeClass("is-invalid");
  $("span").closest(".error-span").remove();
}*/



// Buscar producto
$(document).ready(function()
{
    $("#buscarProductos").autocomplete(
    {
        source: function(req, res) // Función para obtener la lista de productos
        {
            $.ajax(
            {
                url: `/_fetch-products/${req.term}`, // Url donde hará la petición
                data: { term: req.term }, 
                dataType: "json",
                success: function(data) 
                {
                    res($.map(data, function(item)
                    {
                        return {
                            value: item.id,
                            label: item.nombre,
                        }
                    }));
                }
            });
        },
        select: function(event, ui) // Cuando el usuario selecciona un item de la lista
        {
            window.location.href = `/detalleproducto/${ui.item.value}`; // Redireccionar al producto
        }
    });
});

function addUrlParameter(name, value) 
{
    let params = new URLSearchParams(window.location.search);
    params.set(name, value);
    window.location.search = params.toString();
}