package com.yyg.common.data.db

import com.yyg.common.data.db.table.Domain
import com.yyg.common.data.db.table.User
import io.realm.annotations.RealmModule

@RealmModule(classes = [(Domain::class), (User::class)])
class LibraryModule