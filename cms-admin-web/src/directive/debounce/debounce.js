import _debounce from 'lodash/debounce'
let fn = null
const debounce = {
  inserted: function(el, binding) {
    fn = _debounce(binding.value, 2000, {
      leading: true,
      trailing: false
    })
    el.addEventListener('click', fn)
  },
  unbind: function(el) {
    fn && el.removeEventListener('click', fn)
  }
}
export default debounce