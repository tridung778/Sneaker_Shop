document.addEventListener("DOMContentLoaded", () => {
    const subMenus = document.querySelectorAll(".sub-menu");
    const buttons = document.querySelectorAll(".sidebar ul button");
    const sidebar = document.querySelector(".sidebar");
    const sidebarBurger = document.querySelector(".sidebar-burger");

    // Xử lý sự kiện khi nhấp vào nút
    buttons.forEach((button) => {
        button.addEventListener("click", () => onClick(button));
    });

    const onClick = (item) => {
        subMenus.forEach((menu) => {
            menu.style.height = "0px"; // Ẩn tất cả các menu con
        });

        buttons.forEach((button) => {
            button.classList.remove("active"); // Loại bỏ lớp active
            button.setAttribute("aria-expanded", "false"); // Cập nhật thuộc tính aria-expanded
        });

        if (!item.nextElementSibling) {
            // Nếu không có menu con, chỉ cần đánh dấu nút là active
            item.classList.add("active");
            item.setAttribute("aria-expanded", "true");
            return;
        }

        const subMenu = item.nextElementSibling;
        const ul = subMenu.querySelector("ul");

        if (!subMenu.clientHeight) {
            // Nếu menu con đang ẩn, hiển thị nó
            subMenu.style.height = `${ul.clientHeight}px`;
            item.classList.add("active");
            item.setAttribute("aria-expanded", "true");
        } else {
            // Nếu menu con đang hiển thị, ẩn nó
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
