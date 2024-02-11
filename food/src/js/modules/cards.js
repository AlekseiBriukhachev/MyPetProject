import {getResources} from "../services/service";

function cards() {
    //Cards используем классы для карточек

    class CardMenu {
        constructor(src, alt, title, description, price, parentSelector, ...classes) {
            this.src = src;
            this.alt = alt;
            this.title = title;
            this.description = description;
            this.price = price;
            this.classes = classes;
            this.parent = document.querySelector(parentSelector);
            this.exchange = 101.77;
            this.exchangeToRUB();
        }

        exchangeToRUB() {
            this.price = Math.floor(this.price * this.exchange);
        }

        render() {
            const element = document.createElement('div');

            if (this.classes.length === 0) {
                this.element = 'menu__item';
                element.classList.add(this.element);
            } else {
                this.classes.forEach(className => element.classList.add(className));
            }

            element.innerHTML = `
				<img src=${this.src} alt=${this.alt}>
				<h3 class="menu__item-subtitle">${this.title}</h3>
				<div class="menu__item-descr">${this.description}</div>
				<div class="menu__item-divider"></div>
				<div class="menu__item-price">
					<div class="menu__item-cost">Цена:</div>
					<div class="menu__item-total"><span>${this.price}</span> руб/день</div>
				</div>
			`;
            this.parent.append(element);
        }
    }

    // получение ресурсов из jsonDB и передача данных в jsonDB
    getResources("http://localhost:3000/menu")
        .then(data => {
            data.forEach(({ img, altimg, title, descr, price }) => {
                new CardMenu(img, altimg, title, descr, price, '.menu .container', 'menu__item').render();
            });
        });

    // тоже самое что и функции getResources только с использованием библиотеки axios
    // axios.get('http://localhost:3000/menu')
    //     .then(data => {
    //         data.data.forEach(({img, altimg, title, descr, price}) => {
    //             new CardMenu(img, altimg, title, descr, price, '.menu .container', 'menu__item').render();
    //         });
    //     });
}

export default cards;