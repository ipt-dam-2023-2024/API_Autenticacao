package ipt.dam.api.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import ipt.dam.api.R
import ipt.dam.api.model.APIResult
import ipt.dam.api.model.Note
import ipt.dam.api.retrofit.RetrofitInitializer
import ipt.dam.api.ui.adapter.NoteListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)

        listNotes()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            addDummyNote()
        }

    }

    private fun configureList(notes: List<Note>) {
        val recyclerView: RecyclerView = findViewById(R.id.note_list_recyclerview)
        recyclerView.adapter = NoteListAdapter(notes, this)
        val layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }

    private fun listNotes() {

        val call = RetrofitInitializer().noteService().list()
        call.enqueue(object : Callback<List<Note>?> {
            override fun onResponse(call: Call<List<Note>?>?,
                                    response: Response<List<Note>?>?) {
                response?.body()?.let {
                    val notes: List<Note> = it
                    configureList(notes)
                }
            }

            override fun onFailure(call: Call<List<Note>?>?, t: Throwable?) {
                t?.message?.let { Log.e("onFailure error", it) }
            }
        })

    }

    fun addDummyNote() {
        val i = Random.nextInt(100)
        val note = Note("Note "+ i, "Descrition " + i )
        addNote(note) {
            Toast.makeText(this,"Add " + it?.description,Toast.LENGTH_SHORT).show()
            listNotes()
        }
    }

    private fun addNote(note: Note, onResult: (APIResult?) -> Unit){
        val call = RetrofitInitializer().noteService().addNote(note.title, note.description)
        call.enqueue(
            object : Callback<APIResult> {
                override fun onFailure(call: Call<APIResult>, t: Throwable) {
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse( call: Call<APIResult>, response: Response<APIResult>) {
                    val addedNote = response.body()
                    onResult(addedNote)
                }
            }
        )
    }


}