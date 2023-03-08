// store.js
import {reactive} from 'vue'

export const LeftMenuStore = reactive({
    isCollapse: JSON.parse(localStorage.getItem("left_menu_store")
        || JSON.stringify({"isCollapse": false})).isCollapse || false,
    openHide() {
        this.isCollapse = !this.isCollapse;
        localStorage.setItem('left_menu_store', JSON.stringify({"isCollapse": this.isCollapse}));
    }
})