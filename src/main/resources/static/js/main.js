document.addEventListener("DOMContentLoaded", () => {
    const subMenus = document.querySelectorAll(".sub-menu");
    const buttons = document.querySelectorAll(".sidebar ul button");
    const sidebar = document.querySelector(".sidebar");
    const sidebarBurger = document.querySelector(".sidebar-burger");

    buttons.forEach((button) => {
        button.addEventListener("click", () => onClick(button));
    });

    const onClick = (item) => {
        subMenus.forEach((menu) => (menu.style.height = "0px"));
        buttons.forEach((button) => button.classList.remove("active"));
        buttons.forEach((button) => button.setAttribute("aria-expanded", "false"));

        if (!item.nextElementSibling) {
            item.classList.add("active");
            item.setAttribute("aria-expanded", "true");
            return;
        }

        const subMenu = item.nextElementSibling;
        const ul = subMenu.querySelector("ul");

        if (!subMenu.clientHeight) {
            subMenu.style.height = `${ul.clientHeight}px`;
            item.classList.add("active");
            item.setAttribute("aria-expanded", "true");
        } else {
            subMenu.style.height = "0px";
            item.classList.remove("active");
            item.setAttribute("aria-expanded", "false");
        }
    };

    const toggleSidebar = () => {
        sidebar.classList.toggle("collapsed");
    };

    if (sidebarBurger) {
        sidebarBurger.addEventListener("click", toggleSidebar);
    }
});
