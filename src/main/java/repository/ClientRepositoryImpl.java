package repository;

import model.Client;

public class ClientRepositoryImpl extends BaseRepositoryImpl<Client, Integer> implements ClientRepository{

    public ClientRepositoryImpl() {
        super(Client.class);
    }
}
