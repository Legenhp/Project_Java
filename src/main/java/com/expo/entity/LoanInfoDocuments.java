package com.expo.entity;



import lombok.Data;
import org.apache.logging.log4j.message.StringFormattedMessage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "MS_client_crud_documents")
@Data
public class LoanInfoDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loanInfoDocumentId;
    @NotBlank
    private String loanId;
    @NotBlank
    private String docname;
    private Long length;
    @Lob
    private byte[] loanInfodocument;
    private String doctype;
}
