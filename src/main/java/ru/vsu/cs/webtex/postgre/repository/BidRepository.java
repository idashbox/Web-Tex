package ru.vsu.cs.webtex.postgre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.webtex.postgre.model.Bid;

public interface BidRepository extends JpaRepository<Bid, String> { }
