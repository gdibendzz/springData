insert into authors(firstname, lastname, email) value ("Vincenzo", "Bianchi", "vincblanche@tin.it");
insert into authors(firstname, lastname, email) value ("Antonio", "Rossi", "antonred@mail.it");

insert into posts(title, body, publish_date, author_id) 
select 'Lore ipsum...', 'Ciao', null, id from authors where firstname = 'Vincenzo' and lastname = 'Bianchi';
insert into posts(title, body, publish_date, author_id) 
select 'Lore ipsum...', 'Ciao', null, id from authors where firstname = 'Vincenzo' and lastname = 'Bianchi';

insert into comments(email, body, date, post_id) value ("antonred@mail.it", "Lorem ipsum...", "20231315", 14);
insert into comments(email, body, date, post_id) value ("antonred@mail.it", "Lorem ipsum...", "20231315", 15);