 # language: ru
@LoginProfile
Функционал: Смоук тест почтового сервиса gmail.com

  Предыстория:
    Дано открыта страница входа в почтовый ящик Gmail.com

  Сценарий:
    Когда пользователь вводит логин и нажимает Enter и пароль и нажимает Enter
    Тогда открыта старница почтового ящика
    И пользователь нажимает на создание нового письма
    Тогда корректно отображаются поля адресат, тема письма и тело письма
    Если пользователь вводит в поле кому адрес 'адресс' в поле тема 'тема', а в тело письма 'текст тела'
    То черновик письма корректно сохраняется в раздел черновики
    Если пользователь открывает черновик письма
    То разделы адреса, тема письма и тело письма заполнены корректными данными
    Если пользователь нажимает отправить
    То письмо отправляется и удаляется из раздела черновики
    Также добавляется в раздел отправленные
    Тогда пользователь выходит из ученой записи
    И выход происходит корректно

