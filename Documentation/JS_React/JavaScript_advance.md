# 4. Продвинутый JavaScript

## 4.1 Локальные сервера

[MAMP](https://www.mamp.info/de/windows/)

[Open Server](https://ospanel.io/)

## 4.2 JSON

```JavaScript
const person = {
    name : 'Alex',
    tel: '+77973289237'
};

console.log(JSON.stringify(person)); // из объекта в JSON

console.log(JSON.parse(JSON.stringify(person))); // из JSON в объект

```

Создание глубоких копий

```JavaScript
const person = {
    name : 'Alex',
    tel: '+77973289237',
    parents: {
        mom: 'Olga',
        dad: 'Mike'
    },

    const clone = JSON.parse(JSON.stringify(person)); // созданаие нового независимого объекта
    clone.parents.mom = 'Ann';
};
```

## 4.3 AJAX и общение с сервером

```JavaScript

const inputRub = document.querySelector('#rub'),
	inputUsd = document.querySelector('#usd');
```

событие 'change' подсвечивает input пока он выбран

`inputRub.addEventListener('change', () => {`

событие 'input' отслеживает пока активный 'input'

```JavaScript
inputRub.addEventListener('input', () => {
	//создается request
	const request = new XMLHttpRequest();
```

методы XMLHttpRequest

```JavaScript
    request.open(HttpMethod, url, asyncб login, password);
```

- собирает настройки, которые потом помогут сделать запрос
	AJAX запросы ао умолчанию являются асинхронными

```JavaScript
	request.open('GET', 'js/current.json');
```

установка headers

```JavaScript
	request.setRequestHeader('Content-type', 'application/json; charset=utf-8');
```

отправка запроса

```JavaScript
	request.send();
```

свойства запроса

- status - статус код
- statusText - сообщение
- response - ответ от сервера
- readyState - текущее состояние зарпоса
- 'readystatechange' - остлеживает готовность запроса в данный текущий момент
- `request.addEventListener('readystatechange', () => {` данный слушатель используется редко

```JavaScript
	request.addEventListener('load', () => {
		// if (request.readyState === 4 && request.status === 200) {
		if (request.status === 200) {
			console.log(request.response);
			const data = JSON.parse(request.response);
			inputUsd.value = (+inputRub.value / data.current.usd).toFixed(2);
		} else {
			inputUsd.value = 'что-то не то';
		}
	});

});
```

## 4.4 Promise

Технология `Promise` - обещания, если произошло что-то, то мы обещаем, что выполним какое-то действие. И так по цепочке. Пример **callback hell**:

```JavaScript
console.log('Запрос данных...');

setTimeout(() => {
	console.log('Подготовка данных...');

	const product = {
		name: 'TV',
		price: 2000
	};

	setTimeout(() => {
		product.status = 'ordered';
		console.log(product);
	}, 2000);
}, 2000);
```

А как будет выглядеть этот код с применением технологии `promise`:

```JavaScript
console.log('Запрос данных...');

const req = new Promise(function(resolve, reject){
	setTimeout(() => {
		console.log('Подготовка данных...');

		const product = {
			name: 'TV',
			price: 2000
		};
		resolve(product);
	}, 2000);
}).then((product) => {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			product.status = 'ordered';
			resolve(product);
		}, 2000);
	});
}).then(data => {
	data.modify = true;
	return data;
}).then(data => {
	console.log(data);
}).catch(() => {
	console.error('Произошла ошибка');;
}).finally(() => {
	console.log('Finally');
});
```

## 4.5 Fetch API

Для использования Fetch нужно записать `fetch()`

[Fetch Documentation](https://developer.mozilla.org/ru/docs/Web/API/Fetch_API/Using_Fetch)

[{JSON} Placeholder](https://jsonplaceholder.typicode.com)

Fetch использует промисы.

`GET` запросы

```JavaScript
fetch('https://jsonplaceholder.typicode.com/todos/1')
      .then(response => response.json())
      .then(json => console.log(json))
```

`POST` запросы - нужно сделать кое-какие настройки

```JavaScript
fetch('https://jsonplaceholder.typicode.com/posts', {
	method: "POST",
	body: JSON.stringify({name: 'Alex'}),
	headers: {
		'Content-type': 'application/json'
	}
})
.then(response => response.json())
.then(json => console.log(json))
```

## 4.6 Методы перебора массивов

1. Метод `forEach` не возвращает новый массив, просто берет существующий и перебирает все элементы массива.

2. Метод `filter` - фильтрует элементы внутри массива, возвращает новый массив

```JavaScript
const names = ['Ivan', 'Ann', 'Ksenia', 'Voldemart'];

// получить все имена, в которых количество элементов меньше 5
const = shortNames = names.filter(function(name) {
	return name.length < 5;
});
```
3. Метод `map` - берет массив и позволяет изменить каждый элемент внутри него. Соответственно на выходе получается новый массив.

```JavaScript
const answers = ['IvAn', 'AnnA', 'Hello'];

const result answers.map(item => item.toLowerCase());
```
4. Метод `some` - перебирает массив, и если хотя бы один элемент подходит под условие, заданное в функции, то вернет `true`, иначе `false`.

```JavaScript
const some = [4, 'qwe', 'dsvfsbvfd'];

some.some(item => typeof(item) === 'number');
```

5. Метод `every` - перебирает массив, и все элементы подходят под условие, заданное в функции, то вернет `true`, иначе `false`.

```JavaScript
const some = [4, 'qwe', 'dsvfsbvfd'];

some.every(item => typeof(item) === 'number');
```

6. Метод `reduce` - служит для схлопывания массива, или для сбора массива в одно единое целое.

```JavaScript
const arr = [4, 5, 1, 3, 2, 6];

//получить сумму всех элементов массива
const res = arr.reduce((summ, current) => sum + current);

//сложение всех строк в одну
const arr = ['apple', 'pear', 'plum'];

const res = arr.reduce((sum, current) => `${sum}, ${current}`);
```

Пример использования этих методов на практике:

```JavaScript
const obj = {
	ivan: 'person',
	ann: 'person',
	dog: 'animal',
	cat: 'animal'
};

const newArr = Object.entries(obj)
					.filter(item => item[1] === 'person')
					.map(item => item[0]);
```

## 4.7 npm проект. json-server

Создание npm проекта:

1. проинициализировать npm проект `npm init` -> ряд вопросов
	- package name: задать имя если не устраивает по умолчанию
	- version:
	- description:
	- entry point:  главный файл
	- test command:
	- git repository: можем сразу указать url нашего репозитория
	- keywords: ключевые слова по которым мы описываем наш проект
	- author:
	- license:
	- все
2. Появляется файл с настройками `package.json`

3. Установка json-servera [json-server](https://github.com/typicode/json-server)
	- `npm i json-server --save-dev`
4. Появляется файл package-lock.json и папка node-modules со всемя пакетами **!!НЕ ИЗМЕНЯТЬ ЕЕ И НИЧЕГО С НЕЙ НЕ ДЕЛАТЬ!!**
5. Установка пакетов проекта, скачанного с репозотория, когда есть только файл package.json:
	- устанавливается командой `npm i`

6. файл package-lock.json:
	- не трогаем - записаны мини зависимости проекта

7. Запуск json-server:
	- скопировать файл с базой данных (db.json)
	- задать команду `npx json-server db.json` - где db.json - это путь к файлу с данными
	- заустить json-server в gulp [Doc](https://github.com/GrafGenerator/gulp-json-server/blob/master/SAMPLES.md)

## 4.7 Получение данных с сервера. Async/Await

Заполнение карточек сейчас сделано в ручную в скрипте, но можно их заполнение сделать динамически и внешнего файла (db.json) или из базы данных. Можно менять данные через административные панели (админки).
