/* -------- Menu Function -------- */

    /* ---- After Load Function ---- */
    jQuery(document).ready(function() {
        configurationMenu();
    });


    /* ---- Configuration ---- */
        function configurationMenu() {
            var iconMenu = document.getElementById("IconMenu");
            var nav = document.getElementById("Menu").firstElementChild;

            if (nav.childElementCount <= 2) {
                iconMenu.style.display = "none";
            }
        }

        function menu() {
            // Menu Element
            var header = document.getElementById("Header");
            var menu = document.getElementById("Menu");
            var page = document.getElementById("Page");
            var iconMenu = document.getElementById("IconMenu");
            var nav = menu.firstElementChild;

            // Apply some class for the Menu entry
            if (nav.className === "") {
                nav.className = "responsive";
                page.style.display = "none";
                header.className = "headerHeight";
            } else {
                nav.className = "";
                page.style.display = "";
                header.style.height = "";
                header.className = "";
            }
        }