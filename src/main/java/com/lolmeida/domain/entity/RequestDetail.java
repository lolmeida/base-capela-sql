package com.lolmeida.domain.entity;


import lombok.Builder;

/**
 *
 * The Request Information object.
 *
 * @param timestamp
 *         The timestamp of the request.
 * @param uri
 *         The uri of the request.
 * @param transactionId
 *         The transaction id of the request.
 * @param host
 *         The host of the request.
 * @param bmwvin
 *         The bmwvin of the request.
 * @param homeMarket
 *         The home market of the request.
 */
@Builder(toBuilder = true)
public record RequestDetail(

        Long timestamp,
        String uri,
        String transactionId,
        String host,
        String bmwvin,
        String homeMarket
        ) {

}
