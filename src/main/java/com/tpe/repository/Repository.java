package com.tpe.repository;

import com.tpe.domain.Message;

// Uygullamayı gelişime açık hale getirmiş oluyorum.
public interface Repository {

    void save(Message message);
}
