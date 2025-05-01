// Создание коллекции videos
db.createCollection("videos");

// Вставка данных в коллекцию videos
db.videos.insertMany([
    {
        title: "Котики на ферме",
        description: "Музыкальный клип про пушистых котиков, гуляющих по ферме",
        genreId: "1",
        bidId: "101"
    },
    {
        title: "Секреты кухни",
        description: "Кулинарное шоу с рецептами вкусных блюд",
        genreId: "2",
        bidId: "102"
    }
]);

// Создание текстового индекса для поля description
db.videos.createIndex({ description: "text" });

// Создание коллекции genres
db.createCollection("genres");
db.genres.insertMany([
    { id: "1", name: "Музыка" },
    { id: "2", name: "Кулинария" }
]);

// Создание коллекции bids с настройками capped collection
db.createCollection("bids", { capped: true, size: 5242880, max: 5000 });
db.bids.insertMany([
    { id: "101", value: 1000, timestamp: new Date() },
    { id: "102", value: 5000, timestamp: new Date() }
]);
