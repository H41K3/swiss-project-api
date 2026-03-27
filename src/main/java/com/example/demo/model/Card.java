package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cards")
@Entity(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vincula o cartão ao usuário logado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;           // Ex: Nubank
    
    @Column(name = "last_digits")
    private String lastDigits;     // Ex: 4321
    
    private Double totalLimit;     // Limite total do cartão (Ex: 5000.0)
    
    private Double currentInvoice; // Fatura atual (começa em 0.0)
    
    private String color;          // Cor em Hex (Ex: #8A05BE)

    // Construtor para facilitar a criação a partir do DTO
    public Card(User user, String name, String lastDigits, Double totalLimit, String color) {
        this.user = user;
        this.name = name;
        this.lastDigits = lastDigits;
        this.totalLimit = totalLimit;
        this.currentInvoice = 0.0; // Todo cartão nasce com fatura zerada
        this.color = color;
    }
}